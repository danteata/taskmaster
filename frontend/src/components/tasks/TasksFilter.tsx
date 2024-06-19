import React from 'react'
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group'
import { Label } from '@/components/ui/label'

type RadioGroupOption = {
  value: string
  label: string
}

type RadioGroupProps = {
  options: RadioGroupOption[]
  onValueChange: (section: string, value: string) => void
}

const TasksFilter: React.FC<RadioGroupProps> = ({ options, onValueChange }) => {
  return (
    <RadioGroup className="space-y-3 pt-5" onValueChange={onValueChange}>
      {options.map((option) => (
        <div key={option.value} className="flex items-center gap-2">
          <RadioGroupItem value={option.value} id={`r${option.value}`} />
          <Label htmlFor={`r${option.value}`}>{option.label}</Label>
        </div>
      ))}
    </RadioGroup>
  )
}

export default TasksFilter
